const express = require('express')
const app = express()
const port = 3000

app.get('/exam05_1', async (req, res) => {
  await new Promise(resolve => setTimeout(resolve, 5000));
})

app.get('/exam05_2', (req, res) => {
  // res.header("Access-Control-Allow-Origin", "*");
  res.send('console.log("exam05_2 ok!");');
})

app.get('/exam05_x', async (req, res) => {
  await new Promise(resolve => setTimeout(resolve, 5000));
  res.send('var rate = 30000;');
})

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`);
})

