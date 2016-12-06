package com.gpit.tech.es.cs.barafterduedate.notification;

import java.io.File;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author mdmizan
 */
public class SimpleJavaMailer {

    static Logger logger = Logger.getLogger(SimpleJavaMailer.class);
    private String host;
    private String subject;
    private String from;
    private String body;
    private String to;
    private String cc;
    private boolean isEmail;
    private boolean IsPrepareReport;
    private String attachedFile;
    private String attachedFileName;
    private File outputFile;

    public SimpleJavaMailer() {
        this.host = "10.10.20.194";
        logger.info("SimpleJavaMailer:: construstor completed");
    }

    /**
     * @return Returns the subject.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject The subject to set.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return Returns the from.
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from The from to set.
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return Returns the body.
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body The body to set.
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return Returns the to.
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to The to to set.
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * @return Returns the cc.
     */
    public String getCc() {
        return cc;
    }

    /**
     * @param to The to to set.
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * @return Returns the isEmail.
     */
    public boolean getIsEmail() {
        return isEmail;
    }

    /**
     * @param isEmail The isEmail to set.
     */
    public void setIsEmail(boolean isEmail) {
        this.isEmail = isEmail;
    }

    /**
     * @return Returns the IsPrepareReport.
     */
    public boolean getIsPrepareReport() {
        return IsPrepareReport;
    }

    /**
     * @param IsPrepareReport The IsPrepareReport to set.
     */
    public void setIsPrepareReport(boolean IsPrepareReport) {
        this.IsPrepareReport = IsPrepareReport;
    }

    /**
     * @return Returns the attachedFile.
     */
    public String getAttachedFile() {
        return attachedFile;
    }

    /**
     * @param attachedFile The attachedFile to set.
     */
    public void setAttachedFile(String attachedFile) {
        this.attachedFile = attachedFile;
    }

    /**
     * @return Returns the attachedFileName.
     */
    public String getAttachedFileName() {
        return attachedFileName;
    }

    /**
     * @param attachedFileName The attachedFileName to set.
     */
    public void setAttachedFileName(String attachedFileName) {
        this.attachedFileName = attachedFileName;
    }

    /**
     * @return Returns the host.
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host The host to set.
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return Returns the outputFile.
     */
    public File getOutputFile() {
        return outputFile;
    }

    /**
     * @param outputFile The outputFile to set.
     */
    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public boolean sendSimpleMail() throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.host", this.getHost());
        logger.info("SimpleJavaMailer:: sendSimpleMail() host--" + this.getHost());
        props.put("mail.from", this.getFrom());
        Session session = Session.getInstance(props, null);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom();
            msg.setRecipients(Message.RecipientType.TO, this.getTo());
            msg.setSubject(this.getSubject());
            msg.setSentDate(new Date());
            msg.setText(this.getBody());
            Transport.send(msg);
        } catch (MessagingException mex) {
            System.out.println("send failed, exception: " + mex);
        }
        return true;
    }

    public boolean send() throws Exception {

        boolean status = false;

        try {
            logger.info(" Email sending Started " + new Date(System.currentTimeMillis()));
            // Get system properties
            Properties props = System.getProperties();

            // Setup mail server
            props.put("mail.smtp.host", this.getHost());

            // Get session
            Session session = Session.getInstance(props, null);

            // Define message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.getFrom()));
            //message.addRecipient(Message.RecipientType.TO,new InternetAddress(this.getTo()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.getTo()));
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(this.getCc()));
            message.setSubject(this.getSubject());

            // create the message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            //fill message
            messageBodyPart.setText(this.getBody());

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(this.getAttachedFile());
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(this.getAttachedFileName());
            multipart.addBodyPart(messageBodyPart);

            // Put parts in message
            message.setContent(multipart);

            // Send the message
            Transport.send(message);

            try {
                if (!this.getIsPrepareReport()) {
                    this.getOutputFile().delete();
                }
            } catch (Exception ex) {
                logger.info(" Output file does not exist to delete.");
                ex.printStackTrace();
            }
            status = true;
        } catch (Exception ex) {
            logger.fatal(ex.getMessage());
            ex.printStackTrace();
        }
        logger.info(" Email sending completed");
        return status;
    }
}
