/* eslint react/prop-types: 0 */

// import { useState } from "react";
// import { useEffect } from "react";
import { useOutletContext } from "react-router";
import Card from "../components/Card";
// import Nav from "../components/Navbar";
import Pagination from "../components/Pagination";

function Home() {
  // const [games, setGames] = useState([]);
  // const [searchValue, setSearchValue] = useState();

  // const getMovieRequest = async (searchValue) => {
  //   // const url = "https://api.igdb.com/v4/games";

  //   const response = await fetch(
  //     `https://api.rawg.io/api/games?key=38581a1a479949828a178114f3591c8c&search=${searchValue}`,
  //     {
  //       headers: { "content-type": "application/json" },
  //     }
  //   );
  //   //   "https://api.igdb.com/v4/games",
  //   //   {
  //   //     method: "POST",
  //   //     mode: "no-cors",
  //   //     headers: {
  //   //       Accept: "application/json",
  //   //       "Client-ID": "",
  //   //       Authorization: "Bearer ",
  //   //     },
  //   //     body: ["fields name", `search ${searchValue}`, "limit 20"],
  //   //   }
  //   // );

  //   const responseJSON = await response.json();
  //   setGames(responseJSON.results);
  //   console.log(responseJSON);
  // };

  // useEffect(() => {
  //   getMovieRequest(props.searchValue);
  // }, [props.searchValue]);

  const context = useOutletContext();

  return (
    <>
      <div>
        {context.searchValue ? (
          <h1 className="mx-auto w-3/5 text-xl mt-20 mb-5">
            Results for: {context.searchValue}
          </h1>
        ) : (
          <></>
        )}
      </div>
      <div className="m-auto ">
        {context.searchValue ? (
          <div className="grid grid-rows-5 grid-flow-col w-3/5 mx-auto gap-4 justify-between">
            <Card games={context.games} />
          </div>
        ) : (
          <div className="flex">
            <h1 className="mx-auto mt-5">Search for your favorite games!</h1>
          </div>
        )}
      </div>
      {context.searchValue ? (
        <div className="flex my-10">
          <Pagination />
        </div>
      ) : (
        <></>
      )}
    </>
  );
}

export default Home;
