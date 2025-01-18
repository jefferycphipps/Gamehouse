import {useParams} from "react-router-dom";
import React, { useEffect, useState } from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import { userPage } from "../services/APIservice";

function ProfilePage() {

const {username} = useParams();
const[user, setUser] = useState(null);
const navigate = useNavigate();
 const [errorMessage, setErrorMessage] = useState("");

useEffect(() => {
    const fetchUserData = async () => {
        try{
            const response = await userPage(username);
            setUser(response.data);
            setErrorMessage("");
           }catch (error) {
               const loggedIn = localStorage.getItem("username");
               if (!loggedIn) {
                          setErrorMessage("You need to log in first.");
                        }else {
                          setErrorMessage(`An error occurred: ${error.message}`);
                        }
                        navigate("/welcome");
                      }
        };
    fetchUserData();
    }, [username, navigate]);

   return (
          <div className="mt-20 text-2xl mx-auto w-4/5 text-center">
              {errorMessage ? (
                  <h1 className="text-red-500">{errorMessage}</h1>
              ) : (
                  <>
                      {user ? (
                          <>
                              <h1>Welcome, {user.username}</h1>
                              <p>Email: {user.email}</p>
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
