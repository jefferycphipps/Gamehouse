import axios from 'axios';


const apiClient = axios.create({
    baseURL: 'http://localhost:8080/'
  });



  export const fetchEndpoint1 = () => {
    return apiClient.get('/endpoint1');
  };
  export const registerUser = (data) => {
    console.log(data);
    return apiClient.post('/register', data);
  };
  export const loginUser = (data) => {
    console.log(data);
    return apiClient.post('/login', data);
  };

export const search = (data) =>{
    console.log(data);

    return apiClient.post('search/getGames', {params: {searchItem, data}});
    };//to call, must send

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