import { Outlet } from "react-router";
import { useState, useEffect, useRef } from "react";
import Nav from "./Navbar";
import { search } from "../services/APIservice";
import Footer from "./Footer";

function AppLayout() {
  const [games, setGames] = useState([]);
  const [searchValue, setSearchValue] = useState(null);
  const gamesLength = useRef();

  useEffect(() => {
    const getSearchRequest = async (searchVal) => {
      try {
        const response = await search(searchVal);

        const responseJSON = await response.data;
        console.log(responseJSON);
        setGames(responseJSON);
        gamesLength.current = responseJSON.length;

        console.log(responseJSON.length);
      } catch (error) {
        console.log(error);
      }
    };
    getSearchRequest(searchValue).catch(console.error);
  }, [searchValue]);

  console.log(Number(gamesLength.current));
  return (
    <>
      <div className="flex-col items-center w-full justify-center h-max">
        <div className="flex-auto sticky top-0 z-50">
          <Nav searchValue={searchValue} setSearchValue={setSearchValue} />
        </div>

        <Outlet context={{ searchValue, games, gamesLength }} />
      </div>

      <Footer />
    </>
  );
}

export default AppLayout;
