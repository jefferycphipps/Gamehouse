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
        className="hero bg-base-200 min-h-[80vh]"
        style={{
          backgroundImage: `url(${game.boxArtURL})`,
        }}
      >
        <div className="hero-overlay bg-opacity-90"></div>
        <div className="w-4/5 top-0 ">
          <div className="w-full h-3/5 flex gap-10 ">
            <div className="basis-1/3 flex flex-col items-center justify-center gap-5">
              <img src={game.boxArtURL} className="size-auto" />
              <button className="btn btn-primary rounded-3xl w-1/2">
                Wishlist
              </button>
              <button className="btn btn-accent rounded-3xl w-1/2">Save</button>
            </div>
            <div className="basis-1/2 flex flex-col justify-center gap-20">
              <h1 className="mb-5 text-5xl font-bold basis-2/3 text-center">
                {game.name}
              </h1>
              <p className="mb-5  h-1/2">{game.gameDescription}</p>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default GamePage;
