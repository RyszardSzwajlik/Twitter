# Twitter by Ryszard
## Description
A simple social networking application, similar to Twitter, and
exposed through a web API. The application supports the scenarios
below.

## Scenarios

### Posting and user registering
A user is able to post a 140 character message by sending `POST` request to `/post`.
#### Example:
`POST`

`http://localhost:8080/post`

##### Payload request:
```json
{
  "userId": 1,
  "message": {
  	"content": "Some test message"
  }
}
```

##### Response:
`Status 201`

New `userId` registers user. "_Some test message_" is assigned to the user.



### Wall
A user can display a list of the messages they've posted, in reverse chronological order
by sending `GET` request to `/wall`.

**Method arguments:**
* `userId` - (required) Id of the user of whom we want to display messages
* `pageNumber` - (optional) Number of a page that we want to display
* `pageSize` - (optional) Number of messages that we want to display per page

#### Example:
`GET`

`http://localhost:8080/wall?userId=1&pageSize=5&page=1`

##### Response:
````json
{
    "posts": [
        {
            "userId": 1,
            "message": {
                "messageId": 1,
                "content": "Some test message",
                "createTime": "1519629858225"
            }
        }
    ]
}
````

### Following

A user is able to follow another user by sending `PUT` request to `/followUser`. Following doesn't have to be
reciprocal: Alice can follow Bob without Bob having to follow Alice.

**Method arguments:**
* `followUser` - Id of the user who wants to follow another user
* `userToFollowId` - Id of the user that will be followed by previous one

#### Example:
`PUT`

`http://localhost:8080/followUser?userId=1&userToFollowId=2`

##### Response:

`Status 204`

### Timeline
A user is able to see a list of the messages posted by all the people
they follow, in reverse chronological order by sending `GET` request to `/timeline`

**Method arguments:**
* `userId` - (required) Id of the user of whom we want to display messages of followed users
* `pageNumber` - (optional) Number of a page that we want to display
* `pageSize` - (optional) Number of messages that we want to display per page

#### Example:
`GET`

`http://localhost:8080/timeline?userId=1&pageSize=5&page=1`

##### Response:
````json
{
    "posts": [
        {
            "userId": 2,
            "message": {
                "messageId": 2,
                "content": "Some test message of user 2",
                "createTime": "1519629927076"
            }
        }
    ]
}
````