package singispace.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document
public class Permission {

    @Id
    private Long id;
    private String authority;
    @DBRef
    private Set<UserPermission> userPermissions;

    //Constructors

    public Permission() {

    }

    public Permission(Long id, String authority, Set<UserPermission> userPermissions) {
        this.id = id;
        this.authority = authority;
        this.userPermissions = userPermissions;
    }

    public Permission(String authority, Set<UserPermission> userPermissions) {
        this.authority = authority;
        this.userPermissions = userPermissions;
    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Set<UserPermission> getUserPermissions() {
        return userPermissions;
    }

    public void setUserPermissions(Set<UserPermission> userPermissions) {
        this.userPermissions = userPermissions;
    }

}

