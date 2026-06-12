import requests
from bs4 import BeautifulSoup
import pandas as pd
import time
import random
import re

# Configuración de headers para simular un navegador real
HEADERS = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36",
    "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8",
    "Referer": "https://www.onelab.com.ar/"
}

PRODUCTOS_FINAL = []
NOMBRES_VISTOS = set()

CATEGORIAS = [
    {"nombre": "Equipos", "id_bd": 1, "url": "https://www.onelab.com.ar/equipos-consumibles", "size": 30},
    {"nombre": "Análisis de agua", "id_bd": 2, "url": "https://www.onelab.com.ar/insumos-analisis-de-agua", "size": 30},
    {"nombre": "Manejo de líquidos", "id_bd": 4, "url": "https://www.onelab.com.ar/manejo-de-liquidos", "size": 30},
    {"nombre": "Consumibles", "id_bd": 3, "url": "https://www.onelab.com.ar/consumibles", "size": 30},
    {"nombre": "Material de vidrio", "id_bd": 7, "url": "https://www.onelab.com.ar/vidrios", "size": 30},
    {"nombre": "Material plástico", "id_bd": 5, "url": "https://www.onelab.com.ar/material-de-plastico", "size": 30},
    {"nombre": "Material de porcelana y cuarzo", "id_bd": 6, "url": "https://www.onelab.com.ar/material-de-porcelanacuarzo", "size": 30}
]

def extraer_precio_inteligente(texto):
    """Busca el número que aparece inmediatamente después de 'USD'."""
    # Buscamos 'USD' seguido de espacios y luego el número (manejando puntos de miles y comas decimales)
    # Ejemplo: USD 1.744,00
    match = re.search(r'USD\s?([\d\.]+(?:,\d+)?)', texto)
    
    if match:
        precio_str = match.group(1)
        # Limpiamos: quitamos puntos de miles, cambiamos coma por punto decimal
        precio_limpio = precio_str.replace('.', '').replace(',', '.')
        try:
            return round(float(precio_limpio), 2)
        except:
            return 0.0
    return 0.0

print("🚀 Iniciando extracción profesional...")

for cat in CATEGORIAS:
    print(f"\n--- Procesando: {cat['nombre']} ---")
    pagina = 1
    while True:
        url = f"{cat['url']}?pagenumber={pagina}&pagesize={cat['size']}"
        
        # Espera aleatoria para parecer humano y evitar bloqueos
        time.sleep(random.uniform(2.0, 4.0))
        
        try:
            res = requests.get(url, headers=HEADERS, timeout=20)
            if res.status_code != 200:
                print(f" -> Fin de categoría (código {res.status_code})")
                break
                
            soup = BeautifulSoup(res.text, 'html.parser')
            tarjetas = soup.find_all('div', class_='product-item')
            
            if not tarjetas: break
            
            for tarjeta in tarjetas:
                titulo = tarjeta.find('h2', class_='product-title')
                if not titulo: continue
                
                nombre = titulo.get_text().strip()
                if nombre not in NOMBRES_VISTOS:
                    NOMBRES_VISTOS.add(nombre)
                    
                    texto_completo = " ".join(list(tarjeta.stripped_strings))
                    precio = extraer_precio_inteligente(texto_completo)
                    
                    PRODUCTOS_FINAL.append({
                        "name": nombre,
                        "price": precio,
                        "description": f"{nombre} - USD {precio:.2f}",
                        "brandId": 0,
                        "categoryId": cat["id_bd"],
                        "stock": 0,
                        "minStock": 0
                    })
            
            print(f" -> Página {pagina} procesada.")
            pagina += 1
            
        except Exception as e:
            print(f" -> Error en página {pagina}: {e}")
            break

# Exportación
if PRODUCTOS_FINAL:
    df = pd.DataFrame(PRODUCTOS_FINAL)
    df.to_excel("catalogo_onelab_final.xlsx", index=False)
    print(f"\n✅ ¡Éxito! Se guardaron {len(PRODUCTOS_FINAL)} productos en 'catalogo_onelab_final.xlsx'.")