import {search, getbyID} from '../services/APIservice';

import React, {  useState } from 'react';

const Test = () => {
const [postTitle, setPostTitle] = useState('');
const [games, setGames] = useState('');

const handleSubmit =  (event) => {
    event.preventDefault();
    getSearchRequest(postTitle);
};

const getSearchRequest = async (searchValue) => {
    const response = await getbyID(searchValue);
    
    const responseJSON = await response.data;
    console.log(responseJSON);
    setGames(responseJSON);
    console.log(games);
};

    
//axios.post('http://localhost:8080/search/getGames', {},{params: {searchItem: postTitle}})
//.then(response => console.log(response.data))
//.catch(error => console.error(error));


return (
<form onSubmit={handleSubmit}>
<label>
Post Title:
<input type="text" value={postTitle} onChange={(e) => setPostTitle(e.target.value)} />
</label>
<button type="submit">Submit</button>
</form>
);
};

export default Test;