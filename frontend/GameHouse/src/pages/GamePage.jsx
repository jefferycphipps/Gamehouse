import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { getbyID } from "../services/APIservice";

function GamePage() {
  const router = useParams();
  const { gameID } = router;
  const [game, setGame] = useState([]);

  // const getGameRequest = async (gameID) => {
  //   // const url = "https://api.igdb.com/v4/games";

  //   const response = await fetch(
  //     `https://api.rawg.io/api/games/${gameID}?key=38581a1a479949828a178114f3591c8c`,

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
  //   //       "Client-ID": "gvb54zek7ktgnsveftpboeiej9xzgg",
  //   //       Authorization: "Bearer 6dczfi3eooojgi4fh4qgdvf1xz4bau",
  //   //     },
  //   //     body: ["fields name", `search ${searchValue}`, "limit 20"],
  //   //   }
  //   // );

  //   const responseJSON = await response.json();
  //   console.log(responseJSON);
  //   setGame(responseJSON);
  // };

  // useEffect(() => {
  //   getGameRequest(gameID);
  // }, [gameID]);

  useEffect(() => {
    const getGameRequest = async (gameID) => {
      const response = await getbyID(gameID);

      const responseJSON = await response.data;
      console.log(responseJSON);
      setGame(responseJSON);
      console.log(game);
    };
    getGameRequest(gameID).catch(console.error);
  }, [gameID]);

  return (
    <>
      <div
        className="hero bg-base-200 min-h-screen"
        style={{
          backgroundImage: `url(${game.boxArtURL})`,
        }}
      >
        <div className="hero-overlay bg-opacity-90"></div>
        <div className="hero-content text-neutral-content text-center w-4/5">
          <div className="w-4/5">
            <h1 className="mb-5 text-5xl font-bold">{game.name}</h1>
            <p className="mb-5">{game.gameDescription}</p>
            <button className="btn btn-primary">Wishlist</button>
          </div>
        </div>
      </div>
    </>
  );
}

export default GamePage;
