import {REGEXP_URL} from "@/config";
import {StringUtil} from "@/utils";

export const isImage = (fileName: string): boolean => {
	return /\.(png|jpg|jpeg|gif|webp)$/.test(fileName);
}

export const isVideo = (fileName: string): boolean => {
	return /\.(mp4|wmv|avi|mov|webp)$/.test(fileName);
}

export const removeDuplicateElement = <T>(arr :Array<T>): Array<T> => {
	const set: Set<T> = new Set<T>();
	if (arr.length === 0) {
		return new Array<T>()
	}else {
		arr.forEach(v => set.add(v))
		return Array.from(set)
	}
}
export const getRandomNum = (min: number,max: number): number => {
	const range = max - min;
	const rand = Math.random();
	return(min + Math.round(rand * range));
}

export const getRandomTagType = (): string => {
	const typeArr: string[] = ['primary' , 'info' , 'success' , 'warning' , 'error']
	return typeArr[getRandomNum(0, 4)]
}

export const getFileFormat = (fileName: string | null | undefined): string => {
	if (fileName?.indexOf(".") === -1) return '未知格式'
	return fileName!.substring(fileName!.lastIndexOf(".") + 1, fileName!.length);
}

export const getFileSize = (size: number | null | undefined): string => {
	if (!size) {
		return '0 KB'
	}
	if ((size / 1024 / 1024) < 1) {
		return (size / 1024).toFixed(2) + " KB"
	}
	if ((size / 1024 / 1024) > 1024) {
		return (size / 1024 / 1024 / 1024).toFixed(2) + " GB"
	}

	return (size / 1024 / 1024).toFixed(2) + " MB"
}

export const getRealImageUrl = (host: string | null | undefined, filePath: string | null | undefined): string => {
	if (REGEXP_URL.test(filePath!)) return filePath!
	if (!StringUtil.haveLength(host)) return filePath!
	if (host!.endsWith("/")) {
		host = host!.substring(0, host!.length - 1)
	}
	return host + filePath!
}
