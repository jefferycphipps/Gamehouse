import {useParams} from "react-router-dom";
import React, { useEffect, useState } from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import { getPhoto, userPage } from "../services/APIservice";
import "../App.css";
function ProfilePage() {

const [url, setUrl]= useState('');
const defaultPic = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT0IGztaTnh0lfC-HfbBGq_62Q47LFbLePQjMk1jgEZgBcgwVgkE9CzPQAb-NXECLkWrHQ&usqp=CAU";
const[user, setUser] = useState(null);
const navigate = useNavigate();
const [errorMessage, setErrorMessage] = useState("");
const username = localStorage.getItem("username");
    useEffect(() => {
        if(!username) {
        alert("You need to be logged in first.")
        navigate("/welcome");
        console.log("you got me")
            return;
        };

    const fetchUserData = async () => {

        try{
            const response = await userPage(username);
            setUser(response.data);
            if("http://localhost:8080/image/"+username==="no image"){
                setUrl(defaultPic);
            }else{
                setUrl("http://localhost:8080/image/"+username);
            }
            setErrorMessage("");
           }catch (error) {
                setErrorMessage(`An error occurred: ${error.message}`);
                navigate("/welcome");
                console.log("you got me too")
                }
        };
    fetchUserData();
    console.log("url:" + url);
    }, [username, navigate]);

    
   return (
          <div className="mt-20 text-2xl mx-auto w-4/5 text-center">
              {errorMessage ? (
                  <h1 className="text-red-500">{errorMessage}</h1>
              ) : (
                  <>

                      {user ? (
                          <>
                          <div className="w-32 h-32 rounded-full overflow-hidden mx-auto">
                              <img
                              alt="Profile Picture"
                              src={url}
                              className="w-full h-full object-cover"
                              />
                              </div>
                              <h1>Welcome, {user.username}</h1>
                              <p>Email: {user.email}</p>
                               <div className="game-container">
                                          <div className="wishlist">
                                              <h2>Wishlist</h2>
                                              <ul>
                                                  <li>Game 1</li>
                                                  <li>Game 2</li>
                                                  <li>Game 3</li>
                                              </ul>
                                          </div>
                                          <div className="saved-games">
                                              <h2>Saved Games</h2>
                                              <ul>
                                                  <li>Saved Game 1</li>
                                                  <li>Saved Game 2</li>
                                                  <li>Saved Game 3</li>
                                              </ul>
                                          </div>
                                      </div>
                          </>

                      ) : (
                          <h1>Loading...</h1>
                      )}
                  </>
              )}
          </div>
      );
   }

export default ProfilePage;
