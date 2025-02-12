import * as fs from 'node:fs/promises';
import axios from "axios";
import { data } from 'react-router';

const apiClient = axios.create({
  baseURL: "http://localhost:8080/",

  // headers: { "Content-Type": "application/json", accept: "application/json" },
});
const apiClient2 = axios.create({
  baseURL: "http://localhost:8080/",
  headers: { "Content-Type": "application/json", accept: "application/json" },
});

/*  export const fetchEndpoint1 = () => {
    return apiClient.get('/endpoint1');
  }; this is an example of an endpoint */


// Gets Reviews by igdbCode
  export const getReviewsByIgdb = (data) => {
   // console.log("Fetch Reviews!");
    return apiClient.post('/reviews/getReviewsIgdb', data, {
      headers: {"Content-Type": "application/json"
      }
    });
  };

  // Save Reviews by igdbCode, username, gameReview
  export const saveReview = (data) => {
    return apiClient.post('/reviews/save', data, {
      headers: {"Content-Type": "application/json"
      }
    });
  };


  // add game to wishlist by igdbCode, username
  export const addWishlistGame = (data) => {
    //console.log("ADD TO WISHLIST!!!");
    return apiClient.post('/wishlist/addGame', data, {
        headers: {"Content-Type": "application/json"
        }
    });
  };

  // GET wishlist by username
  export const getWishlist = (username) => {
   // console.log("FETCH WISHLIST by Username!!!");
    return apiClient.get('/wishlist/'+ username) 
  };


  // add game to Owned by igdbCode, username
  export const addOwnedGame = (data) => {
    //console.log("ADD TO OWNED!!!");
      return apiClient.post('/owned/addGame', data, {
        headers: {"Content-Type": "application/json"
        }
    }); 
  };

  // GET saved list by username
  export const getSavedlist = (username) => {
    //console.log("FETCH SAVED LIST by Username!!!");
    return apiClient.get('/owned/'+ username) 
  };



export const logOutUser = (data) => {
    return apiClient.get(`/logout`);
    };

export const deleteAccount = (data) => {
  return apiClient.delete("/delete", { data: data });
}; //delete account
export const registerUser = (data) => {
  return apiClient.post("/register", data);
}; //register user
export const loginUser = (data) => {
  return apiClient.post("/login", data);
}; //login user
export const photo = (data) => {
  return apiClient.post("/saveUserImage", data);
}; //save image
export const getPhoto = async (data) => {
  return apiClient.get("/image/" + data);
};
export const userPage = async (username) => {
  return apiClient.post(`/getUser`, username, {
    headers: { "Content-Type": "text/plain" },
  });
};

// added another apiClient but changed the name to apiClients so the start up and search will work
// const apiClients = axios.create({
//     baseURL: 'http://localhost:8080'
//   });

export const getbyID = (data) => {
  const gameID = JSON.stringify(data);
  //console.log(data);
  return apiClient2.post("search/getGamebyID", gameID);
}; //get game by id

export const startup = () => {
  const starter = "go";
  //console.log(starter);
  return apiClient.post("search/startup");
};

export const search = (data) => {
 // console.log("im here");
 // console.log(data);
  return apiClient.post("search/getGames", data);
};

apiClient.interceptors.response.use(
  (response) => response,
  (error) => {
    console.error("API call failed:", error);
    // Handle specific error cases
    if (error.response.status === 401) {
      // Unauthorized
    } else if (error.response.status === 404) {
      // Not found
    }
    return Promise.reject(error);
  }
);

export default apiClient;