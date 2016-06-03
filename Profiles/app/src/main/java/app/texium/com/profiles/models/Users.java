package app.texium.com.profiles.models;

import java.io.Serializable;

/**
 * Created by texiumuser on 29/02/2016.
 */
public class Users implements Serializable {

    private Integer cveUser;
    private Integer idUser;
    private String userName;
    private String password;
    private Integer idActor;
    private String actorName;
    private Integer idRol;
    private Integer idGroup;

    public Users() {

    }

    public Integer getCveUser() {
        return cveUser;
    }

    public void setCveUser(Integer cveUser) {
        this.cveUser = cveUser;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIdActor() {
        return idActor;
    }

    public void setIdActor(Integer idActor) {
        this.idActor = idActor;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }
}
