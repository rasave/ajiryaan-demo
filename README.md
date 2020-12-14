### About Application:
This application is implemented in Spring Boot framework.
- Components used:
    - Maven
    - Java 1.8
- Url:
    - http://localhost:8080/api/
- Assumptions
    - Inventory size assumed is 5
    - If "solar-flare" is true then battery will never be down

### Build and Run on Local

This tool can be run in two ways.
- In eclipse or intellij.
    - Import as existing maven. (command to build project: mv clean install)
    - Select module, right click and then run as spring-boot application.
- Using command line terminal
    - Go to the project directory (cd ~/ajirayaan-demo)
    - command : java -jar target/ajirayaan-demo-0.0.1-SNAPSHOT.jar
   
### REST calls

POST /api/environment/configure
- Content-Type: application/json
- {"temperature": 60,"humidity": 65,"solar-flare": false,"storm": false,"area-map": [[ "dirt", "dirt", "dirt", "water", "dirt" ],[ "dirt", "dirt", "water", "water", "water" ],[ "dirt", "dirt", "dirt", "water", "dirt" ],[ "dirt", "dirt", "dirt", "dirt", "dirt" ],[ "dirt", "dirt", "dirt", "dirt", "dirt" ]]}

PATCH /api/environment
- Content-Type: application/json
- {
   "scenarios":[
      {
         "name":"battery-low",
         "conditions":[
            {
               "type":"rover",
               "property":"battery",
               "operator":"lte",
               "value":2
            }
         ],
         "rover":[
            {
               "is":"immobile"
            }
         ]
      },
      {
         "name":"encountering-water",
         "conditions":[
            {
               "type":"environment",
               "property":"terrain",
               "operator":"eq",
               "value":"water"
            }
         ],
         "rover":[
            {
               "performs":{
                  "collect-sample":{
                     "type":"water-sample",
                     "qty":2
                  }
               }
            }
         ]
      },
      {
         "name":"encountering-storm",
         "conditions":[
            {
               "type":"environment",
               "property":"storm",
               "operator":"eq",
               "value":true
            }
         ],
         "rover":[
            {
               "performs":{
                  "item-usage":{
                     "type":"storm-shield",
                     "qty":1
                  }
               }
            }
         ]
      }
   ],
   "states":[
      {
         "name":"normal",
         "allowedActions":[
            "move",
            "collect-sample"
         ]
      },
      {
         "name":"immobile",
         "allowedActions":[
            "collect-sample"
         ]
      }
   ],
   "deploy-point":{
      "row":3,
      "column":1
   },
   "initial-battery":11,
   "inventory":[
      {
         "type":"storm-shield",
         "quantity":1,
         "priority":1
      }
   ]
}

POST /api/rover/move
- Content-Type: application/json
- Accept: application/json
- {
"direction": "up"
}
- should give
	- 428 Precondition Required
	- Content-Type: application/json
	- {
"message": "Cannot move during a storm"
}


PATCH /api/environment
- Content-Type: application/json
- Accept: application/json
- {
"storm": false
}


POST /api/rover/move
- Content-Type: application/json
- Accept: application/json
- {
"direction": "up"
}

GET /api/rover/status
- Accept: application/json
- should give
- 200 OK
- Content-Type: application/json
{
"rover": {
"location": {
"row": 2,
"column": 3
},
"battery": 8,
"inventory": [
{
"type": "water-sample",
"qty": 2,
"priority": 2
}
]
},
"environment": {
"temperature": 60,
"humidity": 65,
"solar-flare": false,
"storm": false,
"terrain": "water"
}
}


PATCH /api/environment
- Content-Type: application/json
- Accept: application/json
-{
"storm": true
}
Any subsequent requests to /api/rover and subroutes should not return a response