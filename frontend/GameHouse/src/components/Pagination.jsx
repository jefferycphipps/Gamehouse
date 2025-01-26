/* eslint react/prop-types: 0 */

function Pagination({ totalPosts, postPerPage, setCurrentPage }) {
  let pages = [];

  for (let i = 1; i <= Math.ceil(totalPosts / postPerPage); i++) {
    pages.push(i);
  }

  return (
    <div className="flex  justify-center mx-auto gap-2">
      {pages.map((page, index) => {
        return (
          <button
            onClick={() => setCurrentPage(page)}
            className="btn btn-primary h-10 "
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
