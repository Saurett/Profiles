package app.texium.com.profiles.models;

/**
 * Created by texiumuser on 30/05/2016.
 */
public class SpinnerItem {

    private int id;
    private String text;
    private boolean isHint;

    public SpinnerItem(int idItem,String textItem, boolean flag) {
        this.id = idItem;
        this.text = textItem;
        this.isHint = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isHint() {
        return isHint;
    }

    public void setHint(boolean hint) {
        isHint = hint;
    }
}
