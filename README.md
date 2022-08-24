# Authentication Service
When the service is started, The user juan@rodriguez.org is added by default.

## Swagger
URL: http://localhost:8080/swagger-ui/

## Rest API

The REST API to the authentication service is described below.

### Login - Gets the token
>POST /tokens
#### Request
```shell
curl --location --request POST 'http://localhost:8080/tokens?email=juan@rodriguez.org&&password=hunter2' \
--header 'Content-Type: application/json' \
--data-raw '{}'
```

#### Response
```json
{
    "token": "73165a32-610e-4118-9a1f-76e6e2ddd358"
}
```

### Gets the list of Users
>GET /users
#### Request
```shell
curl --location --request GET 'http://localhost:8080/users' \
--header 'Authorization: Bearer 73165a32-610e-4118-9a1f-76e6e2ddd358'
```

#### Response
```json
[
    {
        "id": 1,
        "name": "Juan Rodriguez",
        "email": "juan@rodriguez.org",
        "password": "hunter2",
        "phones": [
            {
                "number": "1234567",
                "cityCode": "1",
                "countryCode": "57"
            }
        ],
        "createdDate": "2022-08-24T03:26:05.721Z",
        "modifiedDate": "2022-08-24T03:26:20.495Z",
        "active": true,
        "token": "73165a32-610e-4118-9a1f-76e6e2ddd358"
    }
]
```

### Gets the User by ID
>GET /users/{userId}
#### Request
```shell
curl --location --request GET 'http://localhost:8080/users/1' \
--header 'Authorization: Bearer 73165a32-610e-4118-9a1f-76e6e2ddd358'
```

#### Response
```json
{
    "id": 1,
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "hunter2",
    "phones": [
        {
            "number": "1234567",
            "cityCode": "1",
            "countryCode": "57"
        }
    ],
    "createdDate": "2022-08-24T03:26:05.721Z",
    "modifiedDate": "2022-08-24T03:26:20.495Z",
    "active": true,
    "token": "73165a32-610e-4118-9a1f-76e6e2ddd358"
}
```

### Creates an User
>POST /users
#### Request
```shell
curl --location --request POST 'http://localhost:8080/users' \
--header 'Authorization: Bearer 73165a32-610e-4118-9a1f-76e6e2ddd358' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Juan Antezana Adrian",
    "email": "juan.antezana@gmail.com",
    "password": "hunter1",
    "phones": [
        {
            "number": "78327312",
            "cityCode": "4",
            "countryCode": "591"
        }
    ]
}'
```

#### Response
```json
{
    "id": 2,
    "name": "Juan Antezana Adrian",
    "email": "juan.antezana@gmail.com",
    "password": "hunter1",
    "phones": [
        {
            "number": "78327312",
            "cityCode": "4",
            "countryCode": "591"
        }
    ],
    "createdDate": "2022-08-24T03:36:16.855Z",
    "modifiedDate": "2022-08-24T03:36:16.855Z",
    "active": true
}
```

### Deactivate an User
>DELETE /users/{userId}
#### Request
```shell
curl --location --request DELETE 'http://localhost:8080/users/1' \
--header 'Authorization: Bearer 73165a32-610e-4118-9a1f-76e6e2ddd358'
```

#### Response
```shell
None
```

### Postman requests
You can import the requests using the file:
```
/resources/Nisum.postman_collection.json
```



