/* eslint react/prop-types: 0 */

import { Link } from "react-router";
const [wishlist, setWishlish] = useState([]);
const [saved, setSaved] = useState([]);

function Card(props) {
  return (
    <>
      {props.games.map((game) => (
        <>
          <Link
            to={`/games/${game?.igdbcode}`}
            className="card rounded-md bg-base-100 relative hover:bg-base-300 hover:cursor-pointer hover:scale-110 hover:z-40 w-52 h-80 mx-auto shadow-xl "
            key={game.igdbcode}
          >
            <figure className="w-full h-4/5">
              <img src={game.boxArtUrl} alt="Shoes" className="size-auto" />
            </figure>
            <div className="card-body p-5 flex-col justify-center items-center mx-auto">
              <h2 className="card-title text-xs text-center">{game.name}</h2>
              <div className="card-actions flex items-end">
                <button
                  onClick={(e) => {
                    e.preventDefault();
                    e.stopPropagation();
                    console.log(game?.igdbcode);
                    setSaved = [...saved, game?.igdbcode];
                    console.log(saved);
                  }}
                  className="btn btn-accent btn-sm text-xs "
                >
                  <svg
                    className="h-6 w-6"
                    viewBox="0 0 16 16"
                    xmlns="http://www.w3.org/2000/svg"
                    version="1.1"
                    fill="none"
                    stroke="#000000"
                  >
                    <path
                      d="m12.75 7.75h-10m5-5v10"
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth="1.5"
                    />
                  </svg>
                </button>
                <button
                  onClick={(e) => {
                    e.preventDefault();
                    e.stopPropagation();
                    console.log(game?.igdbcode);
                    setWishlish = [...wishlist, game?.igdbcode];
                    console.log(wishlist);
                  }}
                  className="btn btn-primary btn-sm text-xs "
                >
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    className="h-6 w-6"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                  >
                    <path
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth="2"
                      d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z"
                    />
                  </svg>
                </button>
              </div>
            </div>
          </Link>
        </>
      ))}
    </>
  );
}

export default Card;
