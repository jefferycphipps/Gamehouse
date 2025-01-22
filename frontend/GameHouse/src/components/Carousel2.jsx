/* eslint react/prop-types: 0 */
import { useState } from "react";
import { Link } from "react-router";

function Carousel2({ images }) {
  const [imageIndex, setImageIndex] = useState(0);

  function next() {
    setImageIndex((index) => {
      if (index === images.length - 1) return 0;
      return index + 1;
    });
  }
  function prev() {
    setImageIndex((index) => {
      if (index === 0) return images.length - 1;
      return index - 1;
    });
  }

  console.log(images);

  return (
    <div className="w-full h-full relative">
      <div className="w-full h-full flex relative overflow-hidden rounded-xl">
        {images.map((img) => (
          <Link
            to={`/games/${img.id}`}
            key={img.img}
            className="w-full flex-shrink-0 flex-grow-0 hover:opacity-85"
            style={{
              translate: `${-100 * imageIndex}%`,
              transition: "translate 500ms ease-in-out",
            }}
          >
            <img src={img.img} className="object-cover w-full h-full block " />
          </Link>
        ))}
      </div>

      <button
        onClick={next}
        className="btn btn-circle p-1 block absolute top-[45%] bottom-0 right-5"
      >
        <svg
          width="40px"
          height="40px"
          viewBox="0 0 20 20"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            d="M8 16a.999.999 0 01-.707-1.707L11.586 10 7.293 5.707a.999.999 0 111.414-1.414l5 5a.999.999 0 010 1.414l-5 5A.997.997 0 018 16z"
            fill="#5C5F62"
          />
        </svg>
      </button>
      <button
        onClick={prev}
        className="btn btn-circle p-1 block top-[45%] absolute left-5"
      >
        <svg
          width="40px"
          height="40px"
          viewBox="0 0 20 20"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            d="M12 16a.997.997 0 01-.707-.293l-5-5a.999.999 0 010-1.414l5-5a.999.999 0 111.414 1.414L8.414 10l4.293 4.293A.999.999 0 0112 16z"
            fill="#5C5F62"
          />
        </svg>
      </button>
    </div>
  );
}

export default Carousel2;
