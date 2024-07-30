[![Codacy Badge](https://app.codacy.com/project/badge/Grade/bee16f3145654047a0505c62aeefd8a2)](https://app.codacy.com/gh/JavaWebinar/topjava/dashboard)

Get meal by id

`curl --location 'http://localhost:8080/topjava/rest/meals/100004'`

Delete meal by id

```
curl --location --request DELETE 'http://localhost:8080/topjava/rest/meals/100004'
```

Get all meals

`curl --location 'http://localhost:8080/topjava/rest/meals'`

Update meal
```
curl --location --request PUT 'http://localhost:8080/topjava/rest/meals/100003' \
--header 'Content-Type: application/json' \
--data '{
"dateTime": "2020-01-30T10:00:00",
"description": "Updated",
"calories": 500
}'
```

Create meal
```
curl --location 'http://localhost:8080/topjava/rest/meals' \
--header 'Content-Type: application/json' \
--data '{
"dateTime": "2024-01-30T10:00:00",
"description": "Created",
"calories": 500
}'
```

Get meals between dates
```
curl --location 'http://localhost:8080/topjava/rest/meals/between?startDate=2020-01-31&endDate=2020-01-31&startTime=10%3A00&endTime=12%3A00'
```