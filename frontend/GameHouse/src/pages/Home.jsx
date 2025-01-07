import { useState } from "react";
import { useEffect } from "react";
import Card from "../components/Card";
import Nav from "../components/Navbar";
import Pagination from "../components/Pagination";

function Home() {
  const [games, setGames] = useState([]);
  const [searchValue, setSearchValue] = useState();

  const getMovieRequest = async (searchValue) => {
    // const url = "https://api.igdb.com/v4/games";

    const response = await fetch(
      `https://api.rawg.io/api/games?key=38581a1a479949828a178114f3591c8c&search=${searchValue}`,
      {
        headers: { "content-type": "application/json" },
      }
    );
    //   method: "POST",
    //   mode: "no-cors",
    //   headers: {
    //     Accept: "application/json",
    //     "Client-ID": "client id",
    //     Authorization: "Bearer auth",
    //   },
    //   body: "fields age_rating",
    // })
    // .then((response) => console.log(response)) //Here is the error
    // .then((data) => {
    //   console.log(data);
    // })
    // .catch((err) => console.log(err));

    const responseJSON = await response.json();
    setGames(responseJSON.results);
    console.log(responseJSON);
  };

  useEffect(() => {
    getMovieRequest(searchValue);
  }, [searchValue]);

  return (
    <>
      <div className="flex-auto">
        <Nav searchValue={searchValue} setSearchValue={setSearchValue} />
      </div>

      {searchValue ? (
        <div className="grid grid-cols-5 w-3/5 mx-auto my-20 gap-2">
          <Card games={games} />
        </div>
      ) : (
        <div className="flex">
          <h1 className="mx-auto">Search for your favorite games!</h1>
        </div>
      )}
      {searchValue ? (
        <div className="flex ">
          <Pagination />
        </div>
      ) : (
        <></>
      )}
    </>
  );
}

export default Home;
