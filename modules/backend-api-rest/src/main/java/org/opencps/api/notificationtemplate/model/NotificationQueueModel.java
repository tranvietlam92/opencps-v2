//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.09.22 at 05:40:35 PM ICT 
//


package org.opencps.api.notificationtemplate.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="notificationQueueId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="notificationType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="classPK" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="payload" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fromUsername" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="toUsername" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="toUserId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="toEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="toTelNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="expireDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="emailSubject" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="emailBody" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="textMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="urlLink" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sendEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sendSMS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "notificationQueueId",
    "notificationType",
    "className",
    "classPK",
    "payload",
    "fromUsername",
    "toUsername",
    "toUserId",
    "toEmail",
    "toTelNo",
    "expireDate",
    "emailSubject",
    "emailBody",
    "textMessage",
    "urlLink",
    "sendEmail",
    "sendSMS"
})
@XmlRootElement(name = "NotificationQueueModel")
public class NotificationQueueModel {

    protected long notificationQueueId;
    @XmlElement(required = true)
    protected String notificationType;
    @XmlElement(required = true)
    protected String className;
    @XmlElement(required = true)
    protected String classPK;
    @XmlElement(required = true)
    protected String payload;
    @XmlElement(required = true)
    protected String fromUsername;
    @XmlElement(required = true)
    protected String toUsername;
    @XmlElement(required = true)
    protected String toUserId;
    @XmlElement(required = true)
    protected String toEmail;
    @XmlElement(required = true)
    protected String toTelNo;
    @XmlElement(required = true)
    protected String expireDate;
    @XmlElement(required = true)
    protected String emailSubject;
    @XmlElement(required = true)
    protected String emailBody;
    @XmlElement(required = true)
    protected String textMessage;
    @XmlElement(required = true)
    protected String urlLink;
    @XmlElement(required = true)
    protected String sendEmail;
    @XmlElement(required = true)
    protected String sendSMS;

    /**
     * Gets the value of the notificationQueueId property.
     * 
     */
    public long getNotificationQueueId() {
        return notificationQueueId;
    }

    /**
     * Sets the value of the notificationQueueId property.
     * 
     */
    public void setNotificationQueueId(long value) {
        this.notificationQueueId = value;
    }

    /**
     * Gets the value of the notificationType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotificationType() {
        return notificationType;
    }

    /**
     * Sets the value of the notificationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotificationType(String value) {
        this.notificationType = value;
    }

    /**
     * Gets the value of the className property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassName() {
        return className;
    }

    /**
     * Sets the value of the className property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassName(String value) {
        this.className = value;
    }

    /**
     * Gets the value of the classPK property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassPK() {
        return classPK;
    }

    /**
     * Sets the value of the classPK property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassPK(String value) {
        this.classPK = value;
    }

    /**
     * Gets the value of the payload property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayload() {
        return payload;
    }

    /**
     * Sets the value of the payload property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayload(String value) {
        this.payload = value;
    }

    /**
     * Gets the value of the fromUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromUsername() {
        return fromUsername;
    }

    /**
     * Sets the value of the fromUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromUsername(String value) {
        this.fromUsername = value;
    }

    /**
     * Gets the value of the toUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToUsername() {
        return toUsername;
    }

    /**
     * Sets the value of the toUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToUsername(String value) {
        this.toUsername = value;
    }

    /**
     * Gets the value of the toUserId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToUserId() {
        return toUserId;
    }

    /**
     * Sets the value of the toUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToUserId(String value) {
        this.toUserId = value;
    }

    /**
     * Gets the value of the toEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToEmail() {
        return toEmail;
    }

    /**
     * Sets the value of the toEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToEmail(String value) {
        this.toEmail = value;
    }

    /**
     * Gets the value of the toTelNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToTelNo() {
        return toTelNo;
    }

    /**
     * Sets the value of the toTelNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToTelNo(String value) {
        this.toTelNo = value;
    }

    /**
     * Gets the value of the expireDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpireDate() {
        return expireDate;
    }

    /**
     * Sets the value of the expireDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpireDate(String value) {
        this.expireDate = value;
    }

    /**
     * Gets the value of the emailSubject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailSubject() {
        return emailSubject;
    }

    /**
     * Sets the value of the emailSubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailSubject(String value) {
        this.emailSubject = value;
    }

    /**
     * Gets the value of the emailBody property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailBody() {
        return emailBody;
    }

    /**
     * Sets the value of the emailBody property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailBody(String value) {
        this.emailBody = value;
    }

    /**
     * Gets the value of the textMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextMessage() {
        return textMessage;
    }

    /**
     * Sets the value of the textMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextMessage(String value) {
        this.textMessage = value;
    }

    /**
     * Gets the value of the urlLink property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlLink() {
        return urlLink;
    }

    /**
     * Sets the value of the urlLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlLink(String value) {
        this.urlLink = value;
    }

    /**
     * Gets the value of the sendEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendEmail() {
        return sendEmail;
    }

    /**
     * Sets the value of the sendEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendEmail(String value) {
        this.sendEmail = value;
    }

    /**
     * Gets the value of the sendSMS property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendSMS() {
        return sendSMS;
    }

    /**
     * Sets the value of the sendSMS property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendSMS(String value) {
        this.sendSMS = value;
    }

}
