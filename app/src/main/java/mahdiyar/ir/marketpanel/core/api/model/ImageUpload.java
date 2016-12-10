package mahdiyar.ir.marketpanel.core.api.model;

/**
 * Created by Mahdiyar on 12/6/2016.
 */

public class ImageUpload {
    private String Address;

    public ImageUpload(String address) {
        Address = address;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
