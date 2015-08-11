#!/bin/bash

# Création de la ligue des Champions
curl -i -X POST \
   -H "Content-Type:application/json" \
   -d \
'{
	"name" : "Ligue des Champions",
    "sport": {
    	"id" : 1,
        "name" : "Football"
    	},
	"urlLogo" : "http://placehold.it/200x200"
}' \
 'http://localhost:8080/api/competitions'

# Création TEAM Barcelona :
curl -i -X POST \
   -H "Content-Type:application/json" \
   -d \
'{
  "name" : "Barcelona",
  "flag" : "http://placehold.it/200x200"
}' \
 'http://localhost:8080/api/teams'

# Création TEAM Sevilla :
curl -i -X POST \
   -H "Content-Type:application/json" \
   -d \
'{
  "name" : "Sevilla",
  "flag" : "http://placehold.it/200x200"
}' \
 'http://localhost:8080/api/teams'

# Création du Match Barcelona-Sevilla
 curl -i -X POST \
    -H "Content-Type:application/json" \
    -d \
 '{
   "localTeam" : {"id" : 1},
   "awayTeam" : {"id" : 2}
 }' \
  'http://localhost:8080/api/competitions/1/matchs'