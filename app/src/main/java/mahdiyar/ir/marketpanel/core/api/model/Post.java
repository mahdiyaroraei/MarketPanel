
package mahdiyar.ir.marketpanel.core.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("cost")
    @Expose
    private Integer cost;
    @SerializedName("offcost")
    @Expose
    private Integer offcost;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private Object updatedAt;
    @SerializedName("is_available")
    @Expose
    private Boolean isAvailable;
    @SerializedName("cat_id")
    @Expose
    private Integer catId;

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 
     * @param desc
     *     The desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 
     * @return
     *     The cost
     */
    public Integer getCost() {
        return cost;
    }

    /**
     * 
     * @param cost
     *     The cost
     */
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    /**
     * 
     * @return
     *     The offcost
     */
    public Integer getOffcost() {
        return offcost;
    }

    /**
     * 
     * @param offcost
     *     The offcost
     */
    public void setOffcost(Integer offcost) {
        this.offcost = offcost;
    }

    /**
     * 
     * @return
     *     The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 
     * @param createdAt
     *     The createdAt
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 
     * @return
     *     The updatedAt
     */
    public Object getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 
     * @param updatedAt
     *     The updatedAt
     */
    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 
     * @return
     *     The isAvailable
     */
    public Boolean getIsAvailable() {
        return isAvailable;
    }

    /**
     * 
     * @param isAvailable
     *     The is_available
     */
    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    /**
     * 
     * @return
     *     The catId
     */
    public Integer getCatId() {
        return catId;
    }

    /**
     * 
     * @param catId
     *     The cat_id
     */
    public void setCatId(Integer catId) {
        this.catId = catId;
    }

}
