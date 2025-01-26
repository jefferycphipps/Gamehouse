/* eslint react/prop-types: 0 */

function Pagination({ totalPosts, postPerPage, setCurrentPage, currentPage }) {
  let pages = [];

  for (let i = 1; i <= Math.ceil(totalPosts / postPerPage); i++) {
    pages.push(i);
  }

  function handleClick(page) {
    setCurrentPage(page);
    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  }

  return (
    <div className="flex  justify-center mx-auto gap-2">
      {pages.map((page, index) => {
        return (
          <button
            onClick={() => handleClick(page)}
            className={`btn  h-10 ${
              page == currentPage ? "btn-neutral" : "btn-active"
            }`}
            key={index}
          >
            {page}
          </button>
        );
      })}
    </div>
  );
}

export default Pagination;
