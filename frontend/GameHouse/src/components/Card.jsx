function Card() {
  return (
    <div className="card bg-base-100 w-48 shadow-xl">
      <figure>
        <img
          src="https://assetsio.gnwcdn.com/co1w4s.jpg?width=1200&height=1200&fit=bounds&quality=70&format=jpg&auto=webp"
          alt="Shoes"
        />
      </figure>
      <div className="card-body">
        <h2 className="card-title">Ultra Street Fighter IV</h2>

        <div className="card-actions ">
          <button className="btn btn-primary">Bookmark</button>
        </div>
      </div>
    </div>
  );
}

export default Card;
