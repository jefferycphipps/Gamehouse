import {useParams} from "react-router-dom";
import React, { useEffect, useState } from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import { userPage } from "../services/APIservice";

function WishListPage() {
const[user, setUser] = useState(null);
const navigate = useNavigate();
const [errorMessage, setErrorMessage] = useState("");
const username = localStorage.getItem("username");

   return (
       <div>
           <h1>hello this is wishlist</h1>
           </div>
       )
    }



export default WishListPage;