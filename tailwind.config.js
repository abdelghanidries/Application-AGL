/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ['./src/main/webapp/*.jsp'],
  theme: {
	  fontSize: {
      'xs': '0.75rem',
      'sm': '0.875rem',
      'base': '1rem',
      'lg': '1.125rem',
      'xl': '1.25rem',
      // Ajoutez des tailles de texte personnalisées si nécessaire
    },
      fontSize: {
      sm: '0.8rem',
      base: '1rem',
      xl: '1.25rem',
      '2xl': '1.563rem',
      '3xl': '1.953rem',
      '4xl': '2.441rem',
      '5xl': '3.052rem',
    },
    extend: {
      colors :{
        brightRed :'hsl(12, 88% ,59%)',
        brightRedLight :'hsl(12, 88% ,69%)',
        brightRedSupLight :'hsl(12, 88%, 95%)',
        darkBlue : 'hsl(228 , 39% ,23%)',
        darkGrayisBlue :'hsl(227, 12%, 61%)',
        veryDarkBlue: 'hsl(233, 12% ,13%)',
        veryPaleRed  : 'hsl(13, 100%, 96%)',
        veryLightGray :'hsl(0 ,0% , 98%)',
        'zinc' :'#d4d4d8',
        'stone' : '#0a0a0a',
        'Emerald': '#10b981',


      }
    },
  },
  plugins: [],
}

