import {defineConfig, loadEnv} from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig(({command, mode}) => {
    // Load env file based on `mode` in the current working directory.
    // Set the third parameter to '' to load all env regardless of the `VITE_` prefix.
    const env = loadEnv(mode, process.cwd(), '')
    console.log(env.auth)
    // console.log(import.meta.env.MODE)
    return {
        // vite config
        server: {
            open: true,
            port: 8080,
            host: '0.0.0.0',
            proxy: {
                '/api': {
                    target: env.server_baseurl,
                    changeOrigin: true, //是否允许跨域
                    // 将api的地址，替换成空
                    // rewrite: path => path.replace(/^\/api/, '')
                }
            },
        },
        plugins: [vue()],
        base: './'
    }
})
