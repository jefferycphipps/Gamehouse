import axios from 'axios';


const apiClient = axios.create({
    baseURL: 'http://localhost:8080/'
  });



/*  export const fetchEndpoint1 = () => {
    return apiClient.get('/endpoint1');
  }; this is an example of an endpoint */


  export const registerUser = (data) => {
    console.log(data);
    return apiClient.post('/register', data);
  };//register user
  export const loginUser = (data) => {
    console.log(data);
    return apiClient.post('/login', data);
  };//login user


 export const startup = () => {
  const starter = "go";
  console.log(starter);
  return apiClient.post('search/startup');
 }

export const search = (data) =>{
    console.log("im here");
    console.log(data);
    return apiClient.post('search/getGames', data);
    };

  apiClient.interceptors.response.use(
    response => response,
    error => {
      console.error('API call failed:', error);
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