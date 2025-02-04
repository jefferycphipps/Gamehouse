# GameHouse
Gamehouse is a website where users can create accounts and save their games to a Save Game list and a Wish List. 
The website works by calling the IGDB - the Internet Game Database and converts that information into a format that Gamehouse uses to store games in a MySQL database. 

The frontend is written in React/Vite with the following tech installed - DaisyUI, React Router, Formik with Yup, Recaptcha, and Axios. T
The backend is written in Java with Spring and uses Googles Gson to parse the information from data received from the API call to the IGDB. 

This website currently has the following features:
User creation with the ability to change image profile pictures.
Game reviews on game page.
Profile sharing.
Save games to a wishlist or save game list.

There are other quality of life features such as light/dark mode and quick search. 
