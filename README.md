# recipes

### Project Description:
[Hyperskill - Recipes](https://hyperskill.org/projects/180)

### Cloud Deployment:
https://recipes-app.azurewebsites.net/


### Features:
- Java 11
- Only authenticated user can add, view, search recipes.
- Only owner can edit, delete a recipe.

### User:
<pre>
{
   "email": "Cook_Programmer@somewhere.com",
   "password": "RecipeInBinary"
}
</pre>

### Recipe:
<pre>
{
   "name": "Fresh Mint Tea",
   "category": "beverage",
   "description": "Light, aromatic and refreshing beverage, ...",
   "ingredients": ["boiled water", "honey", "fresh mint leaves"],
   "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
}
</pre>

### Routes:
<pre>
GET /api/recipe/{id}
GET /api/recipe/search?category={category}
GET /api/recipe/search?name={name}

POST /api/register
POST /api/recipe/new

DELETE /api/recipe/{id}

PUT /api/recipe/{id}
</pre>

### Sample requests:
<pre>
curl -X POST https://recipes-app.azurewebsites.net/api/register \
     -H 'Content-Type: application/json' \
     -d '{
          "email": "newuser@mail.com",
          "password": "12345678"
        }'

curl -X POST https://recipes-app.azurewebsites.net/api/recipe/new \
     -H 'Content-Type: application/json' \
     -u 'newuser@mail.com:12345678' \
     -d '{
           "name": "Fresh Mint Tea",
           "category": "beverage",
           "description": "Light, aromatic and refreshing beverage, ...",
           "ingredients": ["boiled water", "honey", "fresh mint leaves"],
           "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
         }'

curl -X GET https://recipes-app.azurewebsites.net/api/recipe/11 \
     -u 'newuser@mail.com:12345678'

curl -X GET https://recipes-app.azurewebsites.net/api/recipe/search?category=beverage \
     -u 'newuser@mail.com:12345678'

curl -X GET https://recipes-app.azurewebsites.net/api/recipe/search?name=Fresh%20Mint%20Tea \
     -u 'newuser@mail.com:12345678'

curl -X DELETE https://recipes-app.azurewebsites.net/api/recipe/10 \
     -u 'newuser@mail.com:12345678'

curl -X  PUT https://recipes-app.azurewebsites.net/api/recipe/11 \
     -H 'Content-Type: application/json' \
     -u 'newuser@mail.com:12345678' \
     -d '{
           "name": "Mint Tea",
           "category": "beverage",
           "description": "refreshing beverage",
           "ingredients": ["boiled water", "honey", "fresh mint leaves"],
           "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
         }'
</pre>
