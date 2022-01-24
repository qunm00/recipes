# recipes

### Project Description:
[Hyperskill - Recipes](https://hyperskill.org/projects/180)

### Cloud Deployment:
https://recipes-app.azurewebsites.net/

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
GET /api/recipe/search?category={category}&&name={name}

POST /api/register
POST /api/recipe/new

DELETE /api/recipe/{id}

PUT /api/recipe/{id}
</pre>

### Need improvement:
