/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecare;

import java.io.Serializable;


/**
 *
 * @author monjurul.k
 */
public class CampaignHistory implements Serializable {

    private String campaign_id;
    private String campaign_name;
    private String campaign_type;
    private String opt_in;
    private String opt_in_date;
    private String start_date;
    private String expiry_date;
    private String charged_amount;
    private String offer_validity;

    /**
     * @return the campaign_id
     */
    public String getCampaign_id() {
        return campaign_id;
    }

    /**
     * @param campaign_id the campaign_id to set
     */
    public void setCampaign_id(String campaign_id) {
        this.campaign_id = campaign_id;
    }

    /**
     * @return the campaign_name
     */
    public String getCampaign_name() {
        return campaign_name;
    }

    /**
     * @param campaign_name the campaign_name to set
     */
    public void setCampaign_name(String campaign_name) {
        this.campaign_name = campaign_name;
    }

    /**
     * @return the campaign_type
     */
    public String getCampaign_type() {
        return campaign_type;
    }

    /**
     * @param campaign_type the campaign_type to set
     */
    public void setCampaign_type(String campaign_type) {
        this.campaign_type = campaign_type;
    }

    /**
     * @return the opt_in
     */
    public String getOpt_in() {
        return opt_in;
    }

    /**
     * @param opt_in the opt_in to set
     */
    public void setOpt_in(String opt_in) {
        this.opt_in = opt_in;
    }


    /**
     * @return the opt_in_date
     */
    public String getOpt_in_date() {
        return opt_in_date;
    }

    /**
     * @param opt_in_date the opt_in_date to set
     */
    public void setOpt_in_date(String opt_in_date) {
        this.opt_in_date = opt_in_date;
    }

    /**
     * @return the start_date
     */
    public String getStart_date() {
        return start_date;
    }

    /**
     * @param start_date the start_date to set
     */
    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    /**
     * @return the expiry_date
     */
    public String getExpiry_date() {
        return expiry_date;
    }

    /**
     * @param expiry_date the expiry_date to set
     */
    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    /**
     * @return the charged_amount
     */
    public String getCharged_amount() {
        return charged_amount;
    }

    /**
     * @param charged_amount the charged_amount to set
     */
    public void setCharged_amount(String charged_amount) {
        this.charged_amount = charged_amount;
    }

     /**
     * @return the offer_validity
     */
    public String getOfferValidity() {
        return offer_validity;
    }

    /**
     * @param offer_validity the offer_validity to set
     */
    public void setOfferValidity(String offer_period) {
        this.offer_validity = offer_period;
    }
}
