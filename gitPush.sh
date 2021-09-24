##################################################
#
#	Script para Push en GitHub
#	Version: 1.0
#	Autor: Cedric Christoph
#
##################################################
      	git status
      	git add .
        echo "===| MENSAJE COMMIT |========================================="
      	echo " Introduce mensaje para commit y presione ENTER para push"
      	echo ""
      	read -p "==> " msg
	echo "=============================================================="
	echo ""
      	git commit -m "$msg"
	git push
	echo "Archivos subidos :)"
