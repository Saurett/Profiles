package app.texium.com.profiles.models;

import java.io.Serializable;

/**
 * Created by texiumuser on 02/06/2016.
 */
public class CommentProfile implements Serializable {

    private String comment;

    public CommentProfile() {

    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
