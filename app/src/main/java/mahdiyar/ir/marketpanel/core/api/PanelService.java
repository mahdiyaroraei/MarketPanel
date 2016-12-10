package mahdiyar.ir.marketpanel.core.api;

import java.util.List;
import java.util.Map;

import mahdiyar.ir.marketpanel.core.api.model.Product;
import mahdiyar.ir.marketpanel.core.api.model.UserInfo;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Mahdiyar on 12/2/2016.
 */

public interface PanelService {

    @POST("api.aspx?a=get_all_users_info")
    @FormUrlEncoded
    Call<UserInfo> getUserInfo(@Field("user_id") int userId);

    @POST("api.aspx?a=get_posts")
    @FormUrlEncoded
    Call<List<Product>> getProduct(@Field("user_id") int userId, @Field("cat_id") int catId, @Field("order_by") int order);

    @POST("api.aspx?a=upload_img")
    @Multipart
    Call<String> uploadImage(@Part MultipartBody.Part image);

    @POST("api.aspx?a=add_post")
    @FormUrlEncoded
    Call<String> addPost(@Field("title") String title,
                         @Field("desc") String desc,
                         @Field("cost") int cost,
                         @Field("offcost") int offCost,
                         @Field("is_available") boolean available,
                         @Field("user_id") int userId,
                         @Field("images") String images,
                         @Field("cat_id") int catId);

    @POST("api.aspx?a=edit_post")
    @FormUrlEncoded
    Call<String> updatePost(@Field("title") String title,
                            @Field("desc") String desc,
                            @Field("cost") int cost,
                            @Field("offcost") int offCost,
                            @Field("is_available") boolean available,
                            @Field("user_id") int userId,
                            @Field("images") String images,
                            @Field("cat_id") int catId,
                            @Field("post_id") int postId);

    @POST("api.aspx?a=add_store_cat")
    @FormUrlEncoded
    Call<String> addCategory(@Field("image") String image, @Field("name") String name, @Field("store_id") int storeId);

    @POST("api.aspx?a=edit_store_cat")
    @FormUrlEncoded
    Call<String> editCategory(@Field("image") String image, @Field("name") String name, @Field("store_id") int storeId, @Field("cat_id") int catId);

    @POST("api.aspx?a=remove_store_cat")
    @FormUrlEncoded
    Call<String> removeCategory(@Field("cat_id") int catId, @Field("store_id") int storeId);
}
