# Shops API

In order to keep track of shops, retail manager(s) can use RESTful clients and submit POST requests to shop api. API will store the address details (fetched from google maps api) in a memory along with geo co-ordinates of the shop.

Customers can use the same api and find out store near to them.

## Pre-requisites
- Java 8 (JDK 1.8.0_121)
- Spring Boots 1.5.3
- Gradle 3.5
- Google Maps API 0.1.20

### Step 1: Install and build
Go to project directory and build the project
```
cd C:\Users\User\IdeaProjects\shops-api
gradle build
```

### Step 2: Start

```
cd C:\Users\User\IdeaProjects\shops-api
gradle bootRun
```

### Step 3: Usage
Creating Shop
```
POST Request to http://localhost:8080/Shops
Set Content-Type to Application/Json
For example
Body(raw):
{
	"shopName":"Nike Kings Road",
	"shopAddress":{
		"number":"33",
		"postCode":"SW34LX"
		}
}
Output:
{
  "message": "Successfully added the shop."
}
```

Overriding Shop
```
POST Request to http://localhost:8080/Shops
Set Content-Type to Application/Json
For example when shop with 'Nike Kings Road' already exists.
Body(raw):
{
	"shopName":"Nike Kings Road",
	"shopAddress":{
		"number":"33",
		"postCode":"SW34LX"
		}
}
Output:
{
  "message": "Successfully replaced the shop. Older version was: Shop{shopName='Nike Kings Road',ShopAddress{number='33', postCode='SW34LX', coordinates=51.49060340,-0.16116050}}"
}
```



Querying all shops
```
GET Request to http://localhost:8080/Shops
Output:
[
  {
    "shopName": "Nike Kings Road",
    "shopAddress": {
      "number": "33",
      "postCode": "SW34LX",
      "coordinates": {
        "lat": 51.4906034,
        "lng": -0.1611605
      }
    }
  },
  {
    "shopName": "Nike Central",
    "shopAddress": {
      "number": "2",
      "postCode": "N1C4AG",
      "coordinates": {
        "lat": 51.5334034,
        "lng": -0.1250308
      }
    }
  }
]
```

Querying nearest shop
```
GET Request to 
http://localhost:8080/Shop?latitude={latitude}&longitude={longitude}
For Example:
http://localhost:8080/Shop?latitude=51.5334034&longitude=-0.1250308
{
  "message": "Closest shop is Shop{shopName='Nike Central',ShopAddress{number='2', postCode='N1C4AG', coordinates=51.53340340,-0.12503080}} at distance 0.0 KMs."
}
```

Querying health of system (actuator)
```
GET Request to 
http://localhost:8081/health
Output:
{
  "status": "UP"
}
```

## Test
com.db.controller.ShopControllerTest will test configuration and complete flow of application

com.db.utils.TestUtils will test utilities like google maps and distance calculation
