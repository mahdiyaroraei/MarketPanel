
package mahdiyar.ir.marketpanel.core.api.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("banners")
    @Expose
    private List<Banner> banners = new ArrayList<Banner>();
    @SerializedName("categories")
    @Expose
    private List<Category> categories = new ArrayList<Category>();

    /**
     * @return The user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user The user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return The banners
     */
    public List<Banner> getBanners() {
        return banners;
    }

    /**
     * @param banners The banners
     */
    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    /**
     * @return The categories
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * @param categories The categories
     */
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}
