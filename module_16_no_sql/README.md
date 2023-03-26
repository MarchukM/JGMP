# Module_16: NoSQL


Task 1.2: Insert new user
```json
INSERT INTO users (KEY, VALUE) VALUES (
"9e9a5147-8ebb-4344-a55a-b845aa6e2697", 
{
    "id": "9e9a5147-8ebb-4344-a55a-b845aa6e2697",
    "email": "john_doe@epam.com",
    "fullName": "John Doe",
    "birthDate": "1990-10-10",
    "gender": "MALE" }
);
```


Task 2.1: create index for email field
```
CREATE INDEX `idx_find_user_by_email` ON `homework_bucket`.`_default`.`users`(`email`)
```


