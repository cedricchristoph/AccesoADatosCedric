const http = require('http');
const url = require('url');

const port = 9000;
http.createServer( (req, res) => {
    res.write(`Server running on port ${port}`);
    const queryObject = url.parse(req.url, true).query;
    console.log(queryObject);
    let text = "";
    Object.entries(queryObject)
        .forEach( (par) => text += (par[0] + ": " + par[1]));
    res.write(` Received parameter ${text}`);
    res.end();
}).listen(port);