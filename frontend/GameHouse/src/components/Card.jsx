/* eslint react/prop-types: 0 */

function Card(props) {
  return (
    <>
      {props.games.map((game, index) => (
        <div
          className="card bg-base-100 hover:bg-base-300 hover:cursor-pointer w-48 y-20 shadow-xl"
          key={index}
        >
          <figure>
            <img src={game.background_image} alt="Shoes" />
          </figure>
          <div className="card-body p-5">
            <h2 className="card-title text-sm">{game.name}</h2>

            <div className="card-actions ">
              <button className="btn btn-primary btn-sm">
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
                Bookmark
              </button>
            </div>
          </div>
        </div>
      ))}
    </>
  );
}

export default Card;
