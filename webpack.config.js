const webpack = require('webpack');
const path =  require('path')
module.exports = {
  entry: './src/front/index.js',
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        exclude: /node_modules/,
        use: ['babel-loader'],
      },
	  {
        test: /\.js$/,
        exclude: /node_modules/,
        use: ['babel-loader', 'eslint-loader'],
	  },
		{
                          test: /\.css$/,
                          loader: "style-loader!css-loader",                         
                 },

      {test: /bootstrap\/js\//, loader: 'imports?jQuery=jquery' },	 
	 {test: /\.woff(\?v=\d+\.\d+\.\d+)?$/, loader: "url-loader?limit=10000&mimetype=application/font-woff" },
	 {test: /\.woff2(\?v=\d+\.\d+\.\d+)?$/, loader: "url-loader?limit=10000&mimetype=application/font-woff" },
      {test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/, loader: "url-loader?limit=10000&mimetype=application/octet-stream" },
      {test: /\.eot(\?v=\d+\.\d+\.\d+)?$/, loader: "file-loader" },
      {test: /\.svg(\?v=\d+\.\d+\.\d+)?$/, loader: "url-loader?limit=10000&mimetype=image/svg+xml" },
      {test: /\.html$/, loader: 'raw',exclude: /node_modules/},      
      {test: /\.(png|jpg|jpeg|gif|svg|woff|woff2|ttf|eot)$/, loader: 'file-loader', exclude: /node_modules/}
	  ],
  },
  resolve: {
    extensions: ['*', '.js', '.jsx'],
  },
  output: {
    path: `${__dirname}/build/resources/main/static`,
    publicPath: '/',
    filename: 'bundle.js',
  },
  plugins: [
    new webpack.HotModuleReplacementPlugin(),
  ],
  devServer: {
    contentBase: './src/main/resources/static',
    hot: true,
      proxy:{
          '/api':{
              target:'http://localhost:8080/',
              secure:false
          }
      }
  },
};
