import { Outlet } from "react-router";
import { useState, useEffect } from "react";
import Nav from "./Navbar";
import { search } from "../services/APIservice";

function AppLayout() {
  const [games, setGames] = useState([]);
  const [searchValue, setSearchValue] = useState(null);

  useEffect(() => {
    const getSearchRequest = async (searchVal) => {
      const response = await search(searchVal);

      const responseJSON = await response.data;
      console.log(responseJSON);
      setGames(responseJSON);
      console.log(games);
    };
    getSearchRequest(searchValue).catch(console.error);
  }, [searchValue]);

  return (
    <>
      <div className="flex-col items-center w-full justify-center">
        <div className="flex-auto sticky top-0 z-50">
          <Nav searchValue={searchValue} setSearchValue={setSearchValue} />
        </div>

        <Outlet context={{ searchValue, games }} />
      </div>
    </>
  );
}

export default AppLayout;
