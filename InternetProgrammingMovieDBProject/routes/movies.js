var async = require('async');
var express = require('express');
var router = express.Router();
const mysql = require('mysql');
const https = require('https');

router.get('/', function(req, res, next) {
    console.log(req.query);
    let reviews;
    let movie;
    let movieid = req.query.id;
    let getReviews = function(callback) {
        console.log("first");
        const nconnection = mysql.createConnection({
            host: 'if0ck476y7axojpg.cbetxkdyhwsb.us-east-1.rds.amazonaws.com',
            user: 'ek6fr7bzgpv230y8',
            password: 'acdcp33j961libcw',
            database: 'c5qwwjxdop5awyw3'
        });
        nconnection.connect();
        nconnection.query(`
SELECT *
FROM review_table AS rt
LEFT JOIN user_table AS ut ON rt.userid = ut.userid
WHERE movieid = "${movieid}"
ORDER BY rt.date DESC
`, function(error, results, fields) {
            console.log("got reviews", results);
            reviews = results;
            callback(null, 'one');
        });
        nconnection.end();

    };

    //sleep(300);
    function addmovies(callback) {
        console.log("second");
        const connection = mysql.createConnection({
            host: 'if0ck476y7axojpg.cbetxkdyhwsb.us-east-1.rds.amazonaws.com',
            user: 'ek6fr7bzgpv230y8',
            password: 'acdcp33j961libcw',
            database: 'c5qwwjxdop5awyw3'
        });
        connection.connect();
        connection.query(`
SELECT movieid
FROM movie_table
WHERE movieid = "${movieid}"
`, function(error, results, fields) {
            //console.log(results);
            if (error) throw error;
            //console.log('The quotes are: ', results);
            if (results.length != 0) {
                //movie is in database call function
            }
            else {
                addMovie(movieid);
            }
        });
        connection.end();
        callback(null, 'two');
    }

    function result(callback) {
        console.log("third");
        https.get("https://api.themoviedb.org/3/movie/" + movieid + "?api_key=f67394d8499af012dfdbf0c4f1fcb53a", (resp) => {
            let data = '';

            // A chunk of data has been recieved.
            resp.on('data', (chunk) => {
                data += chunk;
            });

            // The whole response has been received. Print out the result.
            resp.on('end', () => {
                //console.log(JSON.parse(data));
                console.log("review is ", reviews);
                movie = data;
                console.log("movie is", movie);
                if (req.session && req.session.username && req.session.username.length) {
                    console.log(req.session.username);
                    res.render('movies', {
                        data: JSON.parse(movie),
                        reviews: reviews,
                        login: true
                    });
                }
                else {
                    res.render('movies', {
                        data: JSON.parse(movie),
                        reviews: reviews
                    })
                }
            });

        }).on("error", (err) => {
            console.log("Error: " + err.message);
        });
        callback(null, 'three');
    }
    console.log("this", typeof getReviews, getReviews);
    console.log(typeof addmovies);
    console.log(typeof result);
    async.series([
        getReviews,
        addmovies,
        result
    ], function(error, success) {
        if (error) { alert('Something is wrong!'); }
        console.log(success);
        console.log('Done!');

    });
});

router.post("/review", function(req, res, next) {
    console.log(req.body);
    let movieid = req.body.movieid;
    let review = req.body.review;
    let score = req.body.score;
    let userid = req.session.userid;
    console.log(req.session.userid);
    var date = new Date();
    console.log(date);
    const dconnection = mysql.createConnection({
        host: 'if0ck476y7axojpg.cbetxkdyhwsb.us-east-1.rds.amazonaws.com',
        user: 'ek6fr7bzgpv230y8',
        password: 'acdcp33j961libcw',
        database: 'c5qwwjxdop5awyw3'
    });
    dconnection.connect();
    dconnection.query(`
INSERT INTO review_table(userid,movieid,review,date,score) VALUES(?, ?, ?, ?, ?)
`, [userid, movieid, review, date, score], (error, results, fields) => {
        console.log(results);
        console.log(error);
        if (error) throw error;
        res.json({
            response: "Successfully added review"
        })
    });
    dconnection.end();
});

router.post("/", function(req, res, next) {
    let movieid = req.body.movieid;
    let stat = req.body.stat;
    console.log("This is the data that was posted: ", movieid, stat);
    const dconnection = mysql.createConnection({
        host: 'if0ck476y7axojpg.cbetxkdyhwsb.us-east-1.rds.amazonaws.com',
        user: 'ek6fr7bzgpv230y8',
        password: 'acdcp33j961libcw',
        database: 'c5qwwjxdop5awyw3'
    });
    dconnection.connect();
    
    if (req.body.stat == 'avg')
    {
        dconnection.query(`
            SELECT AVG(score) AS stat
            FROM review_table
            LEFT JOIN movie_table on review_table.movieid = movie_table.movieid
            WHERE review_table.movieid="${movieid}"
            UNION
            SELECT AVG(score) AS stat
            FROM review_table
            RIGHT JOIN movie_table on review_table.movieid = movie_table.movieid
            WHERE review_table.movieid="${movieid}"
            `, (error, results, fields) => {
                
                if (error) throw error;
                
                res.json({
                    response: "Successfully got stat",
                    stat: results[0].stat.toFixed(2)
                });
            });
    }
    else if (req.body.stat == 'min')
    {
        dconnection.query(`
            SELECT MIN(score) AS stat 
            FROM review_table
            WHERE movieid="${movieid}"
            `, (error, results, fields) => {
                
                if (error) throw error;
                
                res.json({
                    response: "Successfully got stat",
                    stat: results[0].stat.toFixed(2)
                });
            });
    }
    else if (req.body.stat == 'max')
    {
        dconnection.query(`
            SELECT MAX(score) AS stat 
            FROM review_table
            WHERE movieid="${movieid}"
            `, (error, results, fields) => {
                
                if (error) throw error;
                
                res.json({
                    response: "Successfully got stat",
                    stat: results[0].stat.toFixed(2)
                });
            });
    }
    
    dconnection.end();

});

// let getReviews = function(id){
//     let hold = id;
//     console.log(id);
//     const nconnection = mysql.createConnection({
//         host: 'if0ck476y7axojpg.cbetxkdyhwsb.us-east-1.rds.amazonaws.com',
//         user: 'ek6fr7bzgpv230y8',
//         password: 'acdcp33j961libcw',
//         database: 'c5qwwjxdop5awyw3'
//     });
//     nconnection.connect();
//     nconnection.query(`
// SELECT *
// FROM review_table
// WHERE movieid = "${hold}"
// `, function (error,results,fields) {
//         console.log("got reviews",results);
//     });
//     nconnection.end();
// };
let addMovie = function(id) {
    https.get("https://api.themoviedb.org/3/movie/" + id + "?api_key=f67394d8499af012dfdbf0c4f1fcb53a", (resp) => {
        let data = '';
        // A chunk of data has been recieved.
        resp.on('data', (chunk) => {
            data += chunk;
        });
        // The whole response has been received. Print out the result.
        resp.on('end', () => {
            console.log(JSON.parse(data));
            let parsed = JSON.parse(data);
            if (Object.entries(parsed).length !== 0 && parsed.constructor === Object) {
                let api = "https://api.themoviedb.org/3/movie/" + id + "?api_key=f67394d8499af012dfdbf0c4f1fcb53a";
                const connection = mysql.createConnection({
                    host: 'if0ck476y7axojpg.cbetxkdyhwsb.us-east-1.rds.amazonaws.com',
                    user: 'ek6fr7bzgpv230y8',
                    password: 'acdcp33j961libcw',
                    database: 'c5qwwjxdop5awyw3'
                });
                connection.connect();
                connection.query(`
INSERT INTO movie_table(movieid,movietitle,APIcall) VALUES(?, ?, ?)
`, [parsed.id, parsed.title, api], (error, result, fields) => {
                    console.log(result);
                    if (error) throw error;
                });
                connection.end();
            }
        });

    }).on("error", (err) => {
        console.log("Error: " + err.message);
    });
};

function sleep(milliseconds) {
    const date = Date.now();
    let currentDate = null;
    do {
        currentDate = Date.now();
    } while (currentDate - date < milliseconds);
}
module.exports = router;
