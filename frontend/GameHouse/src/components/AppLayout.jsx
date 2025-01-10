import { Outlet } from "react-router";
import { useState, useEffect } from "react";
import Nav from "./Navbar";

function AppLayout() {
  const [games, setGames] = useState([]);
  const [searchValue, setSearchValue] = useState();

  const getSearchRequest = async (searchValue) => {
    // const url = "https://api.igdb.com/v4/games";

    const response = await fetch(
      `https://api.rawg.io/api/games?key=38581a1a479949828a178114f3591c8c&search=${searchValue}`,
      {
        headers: { "content-type": "application/json" },
      }
    );
    //   "https://api.igdb.com/v4/games",
    //   {
    //     method: "POST",
    //     mode: "no-cors",
    //     headers: {
    //       Accept: "application/json",
    //       "Client-ID": "gvb54zek7ktgnsveftpboeiej9xzgg",
    //       Authorization: "Bearer 6dczfi3eooojgi4fh4qgdvf1xz4bau",
    //     },
    //     body: ["fields name", `search ${searchValue}`, "limit 20"],
    //   }
    // );

    const responseJSON = await response.json();
    setGames(responseJSON.results);
    console.log(responseJSON);
  };

  useEffect(() => {
    getSearchRequest(searchValue);
  }, [searchValue]);

  return (
    <>
      <div className="flex-col items-center w-full justify-center">
        <div className="flex-auto sticky  top-0 z-50">
          <Nav searchValue={searchValue} setSearchValue={setSearchValue} />
        </div>

        <Outlet context={{ searchValue, games }} />
      </div>
    </>
  );
}

export default AppLayout;
