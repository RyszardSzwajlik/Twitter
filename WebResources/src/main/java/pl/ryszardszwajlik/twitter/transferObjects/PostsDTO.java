package pl.ryszardszwajlik.twitter.transferObjects;

import java.util.List;

public class PostsDTO
{
    private List<PostDTO> posts;

    public List<PostDTO> getPosts()
    {
        return posts;
    }

    public void setPosts(List<PostDTO> posts)
    {
        this.posts = posts;
    }
}
