/* eslint react/prop-types: 0 */

// import { useState } from "react";
// import { useEffect } from "react";
import { Link, useOutletContext } from "react-router";
import Card from "../components/Card";
// import Nav from "../components/Navbar";
import Pagination from "../components/Pagination";

function Home() {
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
      <>
        {context.searchValue ? (
          <div className="m-auto ">
            <div className="grid grid-cols-3 w-3/5 mx-auto gap-4 ">
              <Card games={context.games} />
            </div>
          </div>
        ) : (
          <div className="mx-auto w-3/5">
            {/* <h1 className="mx-auto mt-5">Search for your favorite games!</h1> */}
            <h1 className="text-3xl"> Developers Picks!</h1>
            <div className="carousel w-full">
              <div
                // to="/games/152063"
                id="slide1"
                className="carousel-item relative w-full"
                // style={{
                //   backgroundImage: `url(https://i0.wp.com/www.consolecreatures.com/wp-content/uploads/2021/09/reviewdeathstrandingDC.jpg)`,
                //   backgroundSize: "cover",
                //   backgroundPosition: "center",
                // }}
              >
                <Link to="/games/152063">
                  <img
                    src="https://i0.wp.com/www.consolecreatures.com/wp-content/uploads/2021/09/reviewdeathstrandingDC.jpg"
                    className="w-full h-full rounded-xl"
                  />
                </Link>
                <div className="absolute left-5 right-5 top-1/2 flex -translate-y-1/2 transform justify-between">
                  <a href="#slide4" className="btn btn-circle z-50">
                    ❮
                  </a>
                  <a href="#slide2" className="btn btn-circle">
                    ❯
                  </a>
                </div>
              </div>
              <div id="slide2" className="carousel-item relative w-full">
                <Link to="/games/109462">
                  <img
                    src="https://play.nintendo.com/images/PLAY-4384-AnimalCrossingNH-GameRelease_3x1_v01.29450787_2cM7X6l.jpg"
                    className="w-full h-full rounded-xl"
                  />
                </Link>
                <div className="absolute left-5 right-5 top-1/2 flex -translate-y-1/2 transform justify-between">
                  <a href="#slide1" className="btn btn-circle">
                    ❮
                  </a>
                  <a href="#slide3" className="btn btn-circle">
                    ❯
                  </a>
                </div>
              </div>
              <div id="slide3" className="carousel-item relative w-full">
                <Link to="/games/132516">
                  <img
                    src="https://www.controllernerds.co.uk/wp-content/uploads/2024/10/phasmophobia-banner.webp"
                    className="w-full h-full rounded-xl"
                  />
                </Link>
                <div className="absolute left-5 right-5 top-1/2 flex -translate-y-1/2 transform justify-between">
                  <a href="#slide2" className="btn btn-circle">
                    ❮
                  </a>
                  <a href="#slide4" className="btn btn-circle">
                    ❯
                  </a>
                </div>
              </div>
              <div id="slide4" className="carousel-item relative w-full">
                <Link to="/games/217590">
                  <img
                    src="https://cdn2.steamgriddb.com/hero_thumb/b1dda493901cc6875f04ebece9cdb093.jpg"
                    className="w-full h-full rounded-xl"
                  />
                </Link>
                <div className="absolute left-5 right-5 top-1/2 flex -translate-y-1/2 transform justify-between">
                  <a href="#slide3" className="btn btn-circle">
                    ❮
                  </a>
                  <a href="#slide1" className="btn btn-circle">
                    ❯
                  </a>
                </div>
              </div>
            </div>
          </div>
        )}
      </>
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
