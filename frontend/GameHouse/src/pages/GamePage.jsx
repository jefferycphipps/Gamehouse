import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { getbyID } from "../services/APIservice";

function GamePage() {
  const router = useParams();
  const { gameID } = router;
  const [game, setGame] = useState([]);

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
