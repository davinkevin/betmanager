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

# Authentication :
curl -b cookie.txt -c cookie.txt -s -i -X GET -H "Content-Type:application/json" -H "Authorization:Basic a2V2aW46a2V2aW4=" 'http://localhost:8080/api/users/me'; cat cookie.txt

# Get LeaderBoard of first competition :
curl -b cookie.txt -c cookie.txt -i -X GET \
                                  -H "Content-Type:application/json" \
                                  -H "X-XSRF-TOKEN:ca036556-5821-480e-b403-fa95a070d897" \
                                  -H "X-Requested-With:XMLHttpRequest" \
                                  'http://localhost:8080/api/competitions/1/leaderboard'