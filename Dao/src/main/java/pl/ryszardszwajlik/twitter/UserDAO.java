package pl.ryszardszwajlik.twitter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "User")
public class UserDAO
{
    @Id
    @Column(nullable = false, unique = true, updatable = false)
    private long userId;

    @OneToMany(mappedBy = "createdBy")
    private List<MessageDAO> messages;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "followers",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "followUserId"))
    private Set<UserDAO> followsUsers = new HashSet<>();

    @ManyToMany(mappedBy = "followsUsers")
    private Set<UserDAO> followedByUsers = new HashSet<>();

    public long getUserId()
    {
        return userId;
    }

    public void setUserId(long userId)
    {
        this.userId = userId;
    }

    public List<MessageDAO> getMessages()
    {
        return messages;
    }

    public void setMessages(List<MessageDAO> messages)
    {
        this.messages = messages;
    }

    public Set<UserDAO> getFollowsUsers()
    {
        return followsUsers;
    }

    public void setFollowsUsers(Set<UserDAO> followsUsers)
    {
        this.followsUsers = followsUsers;
    }

    public Set<UserDAO> getFollowedByUsers()
    {
        return followedByUsers;
    }

    public void setFollowedByUsers(Set<UserDAO> followedByUsers)
    {
        this.followedByUsers = followedByUsers;
    }
}
