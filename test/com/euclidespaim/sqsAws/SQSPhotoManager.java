package com.euclidespaim.sqsAws;

import com.amazonaws.services.sqs.model.Message;
import java.util.List;
import java.util.StringTokenizer;


public class SQSPhotoManager implements Runnable{
    private String queueUrl;
    public static void main(String[] args){
        AWSSimpleQueueServiceUtil awssqsUtil =   AWSSimpleQueueServiceUtil.getInstance();
        /**
         * 1. get the url for your photo queue
         */
        String queueUrl  = awssqsUtil.getQueueUrl(awssqsUtil.getQueueName());
        System.out.println("queueUrl : " + queueUrl);

        /**
         * 2. Add a photo to the queue to be processed
         */

        PhotoFile photo = new PhotoFile();
        photo.setImagePath("C:\\Users\\Kid\\Desktop\\up");
        photo.setOrigName("Tree.jpg");
        photo.setTargetName("Tree_thumb.jpg");

        
        //3. set the photofile in queue for processing

         awssqsUtil.sendMessageToQueue(queueUrl, photo.toString());

        //get the messages from queue

        Thread managerthread = new Thread(new SQSPhotoManager(queueUrl),"T2");
        managerthread.start();

    }

    public SQSPhotoManager(String queueUrl){
        this.queueUrl = queueUrl;
    }

    @Override
    public void run() {
        AWSSimpleQueueServiceUtil awssqsUtil =   AWSSimpleQueueServiceUtil.getInstance();
        boolean flag = true;
        while(flag){
            List<Message> messages =  awssqsUtil.getMessagesFromQueue(this.queueUrl);
            if(messages == null || messages.size() == 0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }else{
                flag = false;
                for (Message message : messages) {
                    String messagePhoto = message.getBody();
                    System.out.println("photo to be processed : " + messagePhoto);
                    StringTokenizer photoTokenizer = new StringTokenizer(messagePhoto,",");
                    String source = null;
                    String target = null;
                    String path = null;

                    source = photoTokenizer.nextToken();
                    target = photoTokenizer.nextToken();
                    path = photoTokenizer.nextToken();
                    System.out.println("source : " + source);
                    System.out.println("target : " + target);
                    System.out.println("path : " + path);
                    /**
                     * generate thumbmail within 150*150 container
                     */
                    PhotoProcessor.generateImage(path, source, target, 150);
                }

                /**
                * finally delete the message
                */
                for (Message message : messages) {
                      awssqsUtil.deleteMessageFromQueue(this.queueUrl, message);
                }

            }
        }
    }
}